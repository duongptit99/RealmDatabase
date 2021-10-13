package com.example.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtSex, edtIclass, edtMath, edtChemistry, edtPhysic;
    private Button btn;
    RealmChangeListener realmChangeListener;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        btn.setOnClickListener(view -> {
            addStudent();
            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
        });

        reFresh();
    }


    public void init() {
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        edtSex = findViewById(R.id.edt_sex);
        edtIclass = findViewById(R.id.edt_iclass);
        edtMath = findViewById(R.id.edt_pointmath);
        edtChemistry = findViewById(R.id.edt_pointchemistry);
        edtPhysic = findViewById(R.id.edt_pointphysic);
        btn = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recyclerview);

        realm = Realm.getDefaultInstance();

        studentAdapter = new StudentAdapter();

        studentList = getAllStudent();
        studentAdapter.setList(studentList);
        recyclerView.setAdapter(studentAdapter);


    }

    public void addStudent() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number id = realm.where(Student.class).max("idStudent");
                int currentID = (id == null) ? 1 : id.intValue() + 1;
                Student student = realm.createObject(Student.class, currentID);

                student.setName(edtName.getText().toString());
                student.setIdClass(edtIclass.getText().toString());
                student.setSex(edtSex.getText().toString());
                student.setPointMath(edtMath.getText().toString());
                student.setPointChemistry(edtChemistry.getText().toString());
                student.setPointPhysic(edtPhysic.getText().toString());

                realm.copyToRealm(student);
            }
        });
    }

    public List<Student> getAllStudent() {
        ArrayList<Student> list = new ArrayList<>();
        RealmResults<Student> results = realm.where(Student.class).findAll();
        for (Student a : results) {
            list.add(a);
        }
        return list;
    }

    private void reFresh() {
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                StudentAdapter adapter = new StudentAdapter();
                adapter.setList(getAllStudent());
                recyclerView.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}