package org.jetbrains.ktor.application

import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.pipeline.*
import org.jetbrains.ktor.response.*

/**
 * Represents server's response
 */
interface ApplicationResponse {
    /**
     * [ApplicationCall] instance this ApplicationResponse is attached to
     */
    val call: ApplicationCall

    /**
     * Headers for this response
     */
    val headers: ResponseHeaders

    /**
     * Cookies for this response
     */
    val cookies: ResponseCookies

    /**
     * Currently set status code for this response, or null if none was set
     */
    fun status(): HttpStatusCode?

    /**
     * Set status for this response
     */
    fun status(value: HttpStatusCode)

    /**
     * Produces HTTP/2 push from server to client or sets HTTP/1.x hint header
     * or does nothing (may call or not call [block]).
     * Exact behaviour is up to host implementation.
     */
    fun push(block: ResponsePushBuilder.() -> Unit) {}
}

open class ApplicationResponsePipeline : Pipeline<ApplicationSendRequest>(Before, Transform, Render, ContentEncoding, TransferEncoding, After, Host) {
    companion object Phases {
        val Before = PipelinePhase("Before")

        val Transform = PipelinePhase("Transform")

        val Render = PipelinePhase("Render")

        val ContentEncoding = PipelinePhase("ContentEncoding")

        val TransferEncoding = PipelinePhase("TransferEncoding")

        val After = PipelinePhase("After")

        val Host = PipelinePhase("Host")
    }
}

data class ApplicationSendRequest(val call: ApplicationCall, val value: Any)


