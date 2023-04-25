package org.zeger.model

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Pavel Zeger
 */
@Entity
data class ClientDetails (

    @Id
    @GeneratedValue
    var id: Long? = 0,
    var name: String? = null,
    var surname: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var basicSalary: Double? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ClientDetails

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(name = $name , surname = $surname , age = $age , email = $email , basicSalary = $basicSalary )"
    }

}