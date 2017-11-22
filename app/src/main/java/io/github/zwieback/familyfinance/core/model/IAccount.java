package io.github.zwieback.familyfinance.core.model;

import android.databinding.Bindable;

import java.math.BigDecimal;

import io.github.zwieback.familyfinance.core.model.converter.BigDecimalToWorthConverter;
import io.github.zwieback.familyfinance.core.model.restriction.AccountRestriction;
import io.requery.CascadeAction;
import io.requery.Column;
import io.requery.Convert;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.ManyToOne;
import io.requery.OneToOne;
import io.requery.PropertyNameStyle;
import io.requery.Table;

@Entity(propertyNameStyle = PropertyNameStyle.FLUENT_BEAN)
@Table(name = "account")
public interface IAccount extends IBaseEntityFolder {

    @Column(nullable = false, value = "true")
    boolean isActive();

    @ForeignKey
    @OneToOne(mappedBy = "id", cascade = CascadeAction.NONE)
    @Column(name = "parent_id")
    IAccount getParent();

    /**
     * May be {@code null} only if {@link #isFolder()}
     */
    @Bindable
    @ManyToOne(cascade = CascadeAction.NONE)
    @Column(name = "currency_id")
    ICurrency getCurrency();

    /**
     * May be {@code null} only if {@link #isFolder()}
     */
    @Bindable
    @ManyToOne(cascade = CascadeAction.NONE)
    @Column(name = "owner_id")
    IPerson getOwner();

    @Bindable
    @Column(nullable = false, length = AccountRestriction.NAME_MAX_LENGTH)
    String getName();

    /**
     * May be {@code null} only if {@link #isFolder()}
     */
    @Bindable
    @Column(name = "initial_balance")
    @Convert(BigDecimalToWorthConverter.class)
    BigDecimal getInitialBalance();

    @Bindable
    @Column(name = "order_code", nullable = false)
    int getOrderCode();
}
