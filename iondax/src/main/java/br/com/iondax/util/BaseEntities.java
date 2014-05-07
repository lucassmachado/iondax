package br.com.iondax.util;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Base of all entities.
 * 
 * @author Branquinho
 */
public abstract class BaseEntities<PK extends Serializable> extends
		AbstractPersistable<PK> implements Serializable {

	private static final long serialVersionUID = -1076604560833245138L;

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
