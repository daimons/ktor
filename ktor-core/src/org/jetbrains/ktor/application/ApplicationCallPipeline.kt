package org.jetbrains.ktor.application

import org.jetbrains.ktor.pipeline.*

open class ApplicationCallPipeline : Pipeline<Unit>(Infrastructure, Call, Fallback) {
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
