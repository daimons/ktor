package org.jetbrains.ktor.application

import org.jetbrains.ktor.util.*

/**
 * Represents a single act of communication between client and server.
 */
interface ApplicationCall {
    /**
     * Application being called
     */
    val application: Application

    /**
     * Client request
     */
    val request: ApplicationRequest

    /**
     * Server response
     */
    val response: ApplicationResponse

    /**
     * Attributes attached to this instance
     */
    val attributes: Attributes

    /**
     * Parameters associated with this call
     */
    val parameters: ValuesMap

    /**
     * Pipeline for transforming request content into receive objects
     */
    val receivePipeline: ApplicationReceivePipeline

    /**
     * Pipeline for transforming responded object into [FinalContent]
     */
    val responsePipeline: ApplicationResponsePipeline
}

/**
 * Sends a [message] as a response
 */
suspend fun ApplicationCall.respond(message: Any) {
    responsePipeline.execute(this, message)
}

