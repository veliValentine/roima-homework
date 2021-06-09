package com.example.demo.service.XmlConverters;

public abstract class ParseXml {
    protected static final String ROOT_START = "<root>";
    protected static final String ROOT_END = "</root>";

    protected static final String ROW_START = "<row>";
    protected static final String ROW_END = "</row>";


    public static String getRootElement(String input) {
        return getElement(input, ROOT_START, ROOT_END);
    }

    protected static String getElement(String input, String startElement, String endElement) throws IllegalArgumentException {
        int startIndex = input.indexOf(startElement);
        int endIndex = input.indexOf(endElement);
        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Missing " + startElement + "-element");
        }
        startIndex += startElement.length();
        if(startIndex > endIndex) {
            throw new IllegalArgumentException(startElement + " comes after " + endElement);
        }
        return input.substring(startIndex, endIndex);
    }

    protected static boolean elementContains(String element, String startElement, String endElement) {
        int startIndex = element.indexOf(startElement);
        int endIndex = element.indexOf(endElement);
        if (startIndex == -1 || endIndex == -1) return false;
        return startIndex + startElement.length() <= endIndex;
    }

    protected static String getNextRowElement(String element) {
        int indexOfEndElement = element.indexOf(ROW_END);
        int endIndexOfEndElement = indexOfEndElement + ROW_END.length();
        return element.substring(endIndexOfEndElement);
    }
}
