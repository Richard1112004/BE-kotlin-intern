package com.example.demo.model
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = true)
    var name: String = "",

    @Column(nullable = false, unique = true)
    val email: String ,

    @Column(nullable = true)
    var password: String = "",

    @Column(nullable = true)
    var phone: String = "",

    @Column(nullable = true)
    var province: String = "",

    @Column(nullable = true)
    var street: String = "",

    @Column(nullable = true)
    var photoURL: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.USER
)

enum class Role {
    ADMIN, USER
}

