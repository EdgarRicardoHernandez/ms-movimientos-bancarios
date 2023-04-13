package com.mssistemamovimientosbancarios.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Commons {
    public static String toJson(Object obj) throws Exception{
        return new ObjectMapper().writeValueAsString(obj);
    }

    public static String convertToBinary(String input, String encoding)
            throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
                .encode(input)
                .array();
        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e ^ 255))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }
}