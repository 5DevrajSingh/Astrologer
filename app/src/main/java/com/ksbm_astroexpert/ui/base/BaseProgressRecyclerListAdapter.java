package com.ksbm_astroexpert.ui.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProgressRecyclerListAdapter<T, L> extends RecyclerView.Adapter<BaseProgressRecyclerListAdapter.ViewHolder> implements Filterable {

    private List<T> items = null;
    private boolean isFooterEnabled = false;
    private AdapterFilter adapterFilter;
    private int defaultSize = 10;
    private boolean showProtoType = false;

    public void setShowProtoType(boolean showProtoType) {
        this.showProtoType = showProtoType;
    }

    protected BaseProgressRecyclerListAdapter( List items) {
        this.items = items;
    }

    public abstract int getLayout(int viewType);

    public abstract void onBind(ViewHolder holder);

    public abstract int getViewType(int position);

    private void addOriginalItems() {
        if (adapterFilter != null) {
            adapterFilter.setOriginalList(items);
        }
    }

    @Override
    public Filter getFilter() {
        return this.adapterFilter;
    }

    public void setAdapterFilter(AdapterFilter adapterFilter) {
        this.adapterFilter = adapterFilter;
    }

    public void appendItems(ArrayList list) {
        this.items.addAll(list);
        notifyDataSetChanged();
        addOriginalItems();
    }

    public void appendAtTop(ArrayList list) {
        this.items.addAll(0, list);
        notifyDataSetChanged();
        addOriginalItems();
    }

    public void addItems(List<T> list) {
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
        addOriginalItems();
    }

    public void addItemsOnSearch(List list) {
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (index <= this.items.size()) {
            this.items.remove(index);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding bind = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false));
        return new ViewHolder<>(bind);
    }

    public T getItem(int index) {
        return items.get(index);
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.setIsRecyclable(false);
        onBind(holder);
        holder.binder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (showProtoType) {
            return defaultSize;
        }
        return (isFooterEnabled) ? (items.size() + 1) : items.size();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addListPagination(List<T> listPagination) {
        for (T o : listPagination) {
            items.add(o);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private V binder;

        public ViewHolder(V v) {
            super(v.getRoot());
            this.binder = v;
        }

        public V getBinder() {
            return binder;
        }
    }

    public void setFooterEnabled() {
        isFooterEnabled = true;
        notifyDataSetChanged();
    }

    public void disableFooter() {
        isFooterEnabled = false;
        notifyDataSetChanged();
    }

    public abstract static class AdapterFilter<T> extends Filter {

        private List<T> originalList;
        private List<T> filteredList;

        public AdapterFilter(BaseProgressRecyclerListAdapter baseRecyclerListAdapter) {
            this.baseRecyclerListAdapter = baseRecyclerListAdapter;
            this.filteredList = new ArrayList<>();
            this.originalList = new ArrayList<>();
        }

        private BaseProgressRecyclerListAdapter baseRecyclerListAdapter;

        public abstract List<T> getFilterResult(List<T> originalList, CharSequence charSequence);

        public void setOriginalList(List<T> originalList) {
            this.originalList.clear();
            List observableList = new ArrayList<>();
            observableList.addAll(originalList);
            this.originalList = observableList;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            final FilterResults results = new FilterResults();
            filteredList.clear();
            filteredList = getFilterResult(originalList, charSequence);
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            baseRecyclerListAdapter.addItemsOnSearch(filteredList);
        }
    }
}
