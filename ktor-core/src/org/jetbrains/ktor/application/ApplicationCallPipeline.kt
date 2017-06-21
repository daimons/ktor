package org.jetbrains.ktor.application

import org.jetbrains.ktor.pipeline.*

val PipelineContext<ApplicationCall>.call: ApplicationCall get() = subject

open class ApplicationCallPipeline() : Pipeline<ApplicationCall>(Infrastructure, Call, Fallback) {
    /**
     * Pipeline for transforming request content into receive objects
     */
    val receivePipeline = ApplicationReceivePipeline()

    /**
     * Pipeline for transforming responded object into [FinalContent]
     */
    val responsePipeline = ApplicationResponsePipeline()

    companion object ApplicationPhase {
        val Infrastructure = PipelinePhase("Infrastructure")
        val Call = PipelinePhase("Call")
        val Fallback = PipelinePhase("Fallback")
    }
}
