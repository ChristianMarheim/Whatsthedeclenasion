package com.example.whatsthedeclenasion;

import androidx.annotation.ColorInt;

public class Data {

    public static String[] normative = {
            "vocabulary form. It expresses a subject in the sentence.",
            "první",
            "used to name or identify people, things, ideas, places, animals.",
            "no changes to its form (no endings) are needed.",
            "ANNA is writing a letter.",
            "To je MŮJ KAMARÁD. This is MY FRIEND.",
            "To jsou ÚZKÉ ULICE. These are NARROW STREETS.",
            "MY FRIEND is A STUDENT. MŮJ KAMARÁD je STUDENT",
            "CZECH REPUBLIC is not A BIG COUNTRY.",
            "ČESKÁ REPUBLIKA není VELKÁ ZEMĚ."};

    public static String[] genitive = {
            "druhý",
            "expresses a possessive or partitive meaning.",
            "bez - without",
            "blízko - near",
            "do - to, into, until",
            "od - from",
            "okolo / kolem - around, about",
            "u - at, by (location)",
            "vedle - next to",
            "z / ze - from"};

    public static String[] dative = {
            "třetí",
            "expresses an indirect object in the sentence.",
            "k / ke - to, towards",
            "kvůli - because of, due to",
            "navzdor(y) - in spite of, despite",
            "proti / naproti- against",
            "vůči - towards, to, against"};

    public static String[] accusative = {
            "čtvrtý",
            "expresses a direct object in the sentence.",
            "na - on, onto (direction)",
            "o - for",
            "pro - for",
            "přes - in spite of",
            "za - for, on behalf of, in the course of",
            "Mít rád + the... "};

    public static String[] vocative = {
            "pátý",
            "form with which we address people",
            "if you want to address someone by name or call out to them by name you use the"};

    public static String[] locative = {
            "šestý",
            "expresses a location.",
            "na - on (location)",
            "o - about",
            "po - after",
            "v / ve - in"};

    public static String[] instrumental = {
            "sedmý",
            "a mean or .... by which an activity is done.",
            "mezi - between",
            "nad - above, over",
            "pod - below, under",
            "před - in front of, before",
            "s / se - with",
            "za - behind",
            "English often has “with” or “by (means of)",
            "onemocnět - become sick with"};

    public static String[][] categories = {
            normative,
            genitive,
            dative,
            accusative,
            vocative,
            locative,
            instrumental };

    public static String[] categoryNames = {
            "normative",
            "genitive",
            "dative",
            "accusative",
            "vocative",
            "locative",
            "instrumental" };

    @ColorInt public static final int MYRED         = 0xCCCC9999;
    @ColorInt public static final int MYGREEN       = 0xCC99CC99;
}