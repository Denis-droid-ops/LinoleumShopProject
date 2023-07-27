package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateRollDto {
    private final String partNum;
    private final String rWidth;
    private final String rLength;
    private final String linoleumId;

    public CreateRollDto(String partNum, String rWidth, String rLength, String linoleumId) {
        this.partNum = partNum;
        this.rWidth = rWidth;
        this.rLength = rLength;
        this.linoleumId = linoleumId;
    }

    public String getPartNum() {
        return partNum;
    }

    public String getrWidth() {
        return rWidth;
    }

    public String getrLength() {
        return rLength;
    }

    public String getLinoleumId() {
        return linoleumId;
    }

    @Override
    public String toString() {
        return "CreateRollDto{" +
                "partNum='" + partNum + '\'' +
                ", rWidth='" + rWidth + '\'' +
                ", rLength='" + rLength + '\'' +
                ", linoleumId='" + linoleumId + '\'' +
                '}';
    }
}
