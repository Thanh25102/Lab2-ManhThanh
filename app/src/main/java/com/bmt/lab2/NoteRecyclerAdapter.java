package com.bmt.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bmt.lab2.pattern.DatePattern;
import com.bmt.lab2.pattern.SharedPreferencesEnum;
import com.bmt.lab2.util.DataUtil;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NoteEntity> notes;
    private IOnClick onClick;
    private Context context;

    public void setData(List<NoteEntity> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }
    public NoteRecyclerAdapter(IOnClick onClick){
        this.onClick = onClick;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        context = parent.getContext();
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        NoteViewHolder noteViewHolder = (NoteViewHolder) holder;
        noteViewHolder.getTitle().setText(notes.get(position).getTitle());
        noteViewHolder.getContent().setText(notes.get(position).getContent());
        //dd-M-yyyy hh:mm:ss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatePattern.DATE_TIME.getValue());
        String date = simpleDateFormat.format(notes.get(position).getCreateTime());
        noteViewHolder.getCreateTime().setText(date);
        noteViewHolder.getWrapper().setOnClickListener(v->onClick.clicked(notes.get(position),position));


    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public interface IOnClick{
        void clicked(NoteEntity noteEntity,Integer position);
    }

    @Getter
    @Setter
    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title,content,createTime;
        private ConstraintLayout wrapper;
        private ImageView btnDelete;
        public NoteViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            createTime = itemView.findViewById(R.id.createTime);
            wrapper = itemView.findViewById(R.id.wrapper);

            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.equals(btnDelete)){
                int position = getLayoutPosition();
                notes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, notes.size());
                DataUtil<NoteEntity> dataUtil = new DataUtil<>();
                dataUtil.removeNote(position,SharedPreferencesEnum.KEY.getValue(),SharedPreferencesEnum.SHAREDPREFERENCESNAME.getValue(), context);
            }
        }
    }
}
