package org.zeger.service

import org.zeger.model.ClientDetails
import org.zeger.repository.ClientDetailsRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

/**
 * @author Pavel Zeger
 */
@ApplicationScoped
@Transactional
class ClientDetailsService {

    @Inject
    lateinit var clientDetailsRepository: ClientDetailsRepository

    fun saveClient(clientDetails: ClientDetails) {
        clientDetailsRepository.persist(clientDetails)
    }

    fun findAll(): List<ClientDetails> {
        return clientDetailsRepository.listAll()
    }

    fun findById(id: Long): ClientDetails? {
        return clientDetailsRepository.findByIdOptional(id).orElseGet(null)
    }

    fun findByName(name: String): ClientDetails? {
        return clientDetailsRepository.findByNameOptional(name)
    }

    fun deleteClient(id: Long) {
        clientDetailsRepository.deleteById(id)
    }

    fun updateClient(clientDetails: ClientDetails) {
        val query: String = "name = ${clientDetails.name}, " +
                "surname = ${clientDetails.surname}, " +
                "age = ${clientDetails.age}, " +
                "email = ${clientDetails.email}, " +
                "basicSalary = ${clientDetails.basicSalary}, " +
                "where id = ${clientDetails.id}"
        clientDetailsRepository.update(query)
    }

}