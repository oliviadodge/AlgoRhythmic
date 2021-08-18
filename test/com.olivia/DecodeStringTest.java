package com.olivia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecodeStringTest {

    @Test
    void decodeString() {
        DecodeString decoder = new DecodeString();

        String input = "3[a]2[bc]";
        String output = "aaabcbc";

        assertEquals(output, decoder.decodeString(input));

        input = "3[a2[c]]";
        output = "accaccacc";

        assertEquals(output, decoder.decodeString(input));

        input = "2[abc]3[cd]ef";
        output = "abcabccdcdcdef";

        assertEquals(output, decoder.decodeString(input));

        input = "abc3[cd]xyz";
        output = "abccdcdcdxyz";

        assertEquals(output, decoder.decodeString(input));

        input = "3[a2[c3[da]]]";
        output = "acdadadacdadadaacdadadacdadadaacdadadacdadada";

        assertEquals(output, decoder.decodeString(input));
    }
}