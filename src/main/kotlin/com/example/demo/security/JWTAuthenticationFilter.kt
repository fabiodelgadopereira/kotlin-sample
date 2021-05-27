package com.example.demo.security


import com.example.demo.config.SecurityProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.io.PrintWriter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



class JWTAuthenticationFilter(
        private val authManager: AuthenticationManager,
        private val securityProperties: SecurityProperties
) : UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
            req: HttpServletRequest,
            res: HttpServletResponse?
    ): Authentication {
        return try {
            val mapper = jacksonObjectMapper()

            val creds = mapper
                    .readValue<com.example.demo.model.User>(req.inputStream)

            authManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            creds.username,
                            creds.password,
                            ArrayList<GrantedAuthority>()
                    )
            )
        } catch (e: IOException) {
            throw AuthenticationServiceException(e.message)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
            req: HttpServletRequest,
            res: HttpServletResponse,
            chain: FilterChain?,
            auth: Authentication
    ) {
        val claims: MutableList<String> = mutableListOf()
        if (auth.authorities.isNotEmpty())
            auth.authorities.forEach { a -> claims.add(a.toString()) }

        val token = Jwts.builder()
                .setSubject((auth.principal as User).username)
                .claim("auth", claims)
                .setExpiration(Date(Date().time + 2 * 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(securityProperties.secret.toByteArray()), SignatureAlgorithm.HS512)
                .compact()

        val out: PrintWriter = res.getWriter()
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        out.print("{\"access_token\" : \"${token}\"}");
        out.flush();

    }
}