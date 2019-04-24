package com.firstlinesoftware.lesson2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class User implements Externalizable {

    private int id;

    private String name;

    private String email;

    private transient String phone;

    private boolean confirmed;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        id = in.readInt();
    }
}
