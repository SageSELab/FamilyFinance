package io.github.zwieback.familyfinance.core.lifecycle.creator;

import android.content.Context;
import android.support.annotation.StringRes;

import java.util.Comparator;
import java.util.concurrent.Callable;

import io.github.zwieback.familyfinance.core.model.IBaseEntity;
import io.github.zwieback.familyfinance.core.preference.config.DatabasePrefs;
import io.reactivex.Observable;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

public abstract class EntityCreator<E extends IBaseEntity>
        implements Callable<Observable<Iterable<E>>>, Comparator<E> {

    protected final Context context;
    protected final ReactiveEntityStore<Persistable> data;
    protected final DatabasePrefs databasePrefs;

    protected EntityCreator(Context context, ReactiveEntityStore<Persistable> data) {
        this.context = context;
        this.data = data;
        this.databasePrefs = DatabasePrefs.with(context);
    }

    protected abstract Iterable<E> buildEntities();

    @Override
    public Observable<Iterable<E>> call() {
        return data.insert(buildEntities()).toObservable();
    }

    protected String getString(@StringRes int resId) {
        return context.getResources().getString(resId);
    }
}
