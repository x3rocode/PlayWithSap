package com.example.playwithsap.network.model

import android.util.Log
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import java.util.*

@Serializable(with = TableDataDtoSerializer::class)
data class TableDataDto(

    val type: String,
    val data: Any
    )


object TableDataDtoSerializer : KSerializer<TableDataDto> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TableDataDto") {
        element("type", serialDescriptor<String>())
        element("data", buildClassSerialDescriptor("Any"))
    }


    @Suppress("UNCHECKED_CAST")
    private val dataTypeSerializers: Map<String, KSerializer<Any>> =
        mapOf(
            "String" to serializer<String>(),
            "Int" to serializer<Int>(),
            //list them all
        ).mapValues { (_, v) -> v as KSerializer<Any> }

    private fun getPayloadSerializer(dataType: String): KSerializer<Any> = dataTypeSerializers[dataType]
        ?: throw SerializationException("Serializer for class $dataType is not registered in PacketSerializer")

    override fun serialize(encoder: Encoder, value: TableDataDto) {
        Log.d("ddddddddd", "serial")
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.type)
            encodeSerializableElement(descriptor, 1, getPayloadSerializer(value.type), value.data)
        }
    }

    @ExperimentalSerializationApi
    override fun deserialize(decoder: Decoder): TableDataDto = decoder.decodeStructure(descriptor) {
        Log.d("ddddddddd", "deseri")

        if (decodeSequentially()) {
            val type = decodeStringElement(descriptor, 0)
            val data = decodeSerializableElement(descriptor, 1, getPayloadSerializer(type))
            TableDataDto(type, data)
        } else {
            require(decodeElementIndex(descriptor) == 0) { "dataType field should precede payload field" }
            val type = decodeStringElement(descriptor, 0)
            val data = when (val index = decodeElementIndex(descriptor)) {
                1 -> decodeSerializableElement(descriptor, 1, getPayloadSerializer(type))
                CompositeDecoder.DECODE_DONE -> throw SerializationException("payload field is missing")
                else -> error("Unexpected index: $index")
            }
            TableDataDto(type, data)
        }
    }


}