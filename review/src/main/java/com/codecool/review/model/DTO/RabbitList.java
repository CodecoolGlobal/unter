package com.codecool.review.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitList {

    public List<RabbitSendOut> data = new ArrayList<RabbitSendOut>();

    public void addElement(RabbitSendOut rabbitSendOut) {
        data.add(rabbitSendOut);
        //return animals;
    }
}
