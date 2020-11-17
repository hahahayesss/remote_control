package com.r00t.remotecontrol.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r00t.remotecontrol.R;
import com.r00t.remotecontrol.data.log.LogEntity;
import com.r00t.remotecontrol.domain.util.Commons;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.hilt.android.qualifiers.ApplicationContext;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<LogEntity> logEntityList;

    @Inject
    public LogsAdapter(@ApplicationContext Context context) {
        logEntityList = Collections.emptyList();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_log_entity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogEntity logEntity = logEntityList.get(position);
        holder.idTextView.setText(String.valueOf(logEntity.getId()));
        holder.timestampTextView.setText(Commons.timestampToDateAndTime(logEntity.getTimestamp()));
        holder.eventTextView.setText(logEntity.getEvent());
    }

    @Override
    public int getItemCount() {
        return logEntityList.size();
    }

    public void setLogEntityList(List<LogEntity> logEntityList) {
        this.logEntityList = logEntityList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_text_view)
        protected TextView idTextView;
        @BindView(R.id.timestamp_text_view)
        protected TextView timestampTextView;
        @BindView(R.id.event_text_view)
        protected TextView eventTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
