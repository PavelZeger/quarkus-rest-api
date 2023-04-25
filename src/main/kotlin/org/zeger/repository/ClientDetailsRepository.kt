package org.zeger.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import org.zeger.model.ClientDetails
import javax.enterprise.context.ApplicationScoped

/**
 * @author Pavel Zeger
 */
@ApplicationScoped
interface ClientDetailsRepository: PanacheRepository<ClientDetails> {

    fun findByNameOptional(name: String): ClientDetails? = find("name", name).firstResult()

}