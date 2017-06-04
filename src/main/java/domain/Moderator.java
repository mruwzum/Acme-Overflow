/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 * Created by daviddelatorre on 15/5/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Moderator extends User {


}
