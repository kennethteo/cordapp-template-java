package com.template.states;

import com.template.contracts.TokenContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

@BelongsToContract(TokenContract.class)
public final class TokenState implements ContractState {
    @NotNull
    private final Party issuer;

    @NotNull
    private final Party holder;
    private final long quantity;

    public TokenState(@NotNull final Party issuer, @NotNull final Party holder, final long quantity) {
        if (issuer == null) throw new NullPointerException("issuer cannot be null");
        if (holder == null) throw new NullPointerException("holder cannot be null");
        this.issuer = issuer;
        this.holder = holder;
        this.quantity = quantity;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return Collections.singletonList(holder);
    }

    @NotNull
    public Party getIssuer() {
        return issuer;
    }

    @NotNull
    public Party getHolder() {
        return holder;
    }

    public long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenState that = (TokenState) o;

        if (quantity != that.quantity) return false;
        if (!issuer.equals(that.issuer)) return false;
        return holder.equals(that.holder);
    }

    @Override
    public int hashCode() {
        int result = issuer.hashCode();
        result = 31 * result + holder.hashCode();
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }
}
