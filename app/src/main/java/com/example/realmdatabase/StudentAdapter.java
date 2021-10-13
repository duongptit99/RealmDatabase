package com.example.realmdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> list;

    public void setList(List<Student> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = list.get(position);
        holder.edtID.setText(student.getIdStudent()+"");
        holder.edtName.setText(student.getName());
        holder.edtSex.setText(student.getSex());
        holder.edtIclass.setText(student.getIdClass());
        holder.edtMath.setText(student.getPointMath());
        holder.edtPhysic.setText(student.getPointPhysic());
        holder.edtChemistry.setText(student.getPointChemistry());
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText edtID, edtName, edtSex, edtIclass, edtMath, edtChemistry, edtPhysic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edtID = itemView.findViewById(R.id.edt_item_id);
            edtName = itemView.findViewById(R.id.edt_item_name);
            edtSex = itemView.findViewById(R.id.edt_item_sex);
            edtIclass = itemView.findViewById(R.id.edt_item_iclass);
            edtMath = itemView.findViewById(R.id.edt_item_pointmath);
            edtPhysic = itemView.findViewById(R.id.edt_item_pointphysic);
            edtChemistry = itemView.findViewById(R.id.edt_item_pointchemistry);
        }
    }
}
