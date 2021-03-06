package com.example.proje.com.example.proje.ogrenci_veli_activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proje.com.example.proje.kayit_ve_login_activitiyleri.MainActivity;
import com.example.proje.R;
import com.example.proje.com.example.proje.tanımliclasslar.Ogrenci;
import com.example.proje.com.example.proje.tanımliclasslar.Ogretmen;
import com.example.proje.com.example.proje.tanımliclasslar.Veli;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;

public class fragmentKimlikBilgisi extends Fragment {
    private Ogretmen ogretmen =null;
    private Ogrenci ogrenci = null;
    private Veli veli = null;

    MainActivity mainActivity;
    TextView adSoyad, email,tcNo, sinif;
    //CircularImageView resim;
    ImageView resim;
    LinearLayout linearLayout;
    FirebaseStorage storage;
    StorageReference storageReference;
    File localFile;
    Button parolaSifirla;
    Context context;

    public fragmentKimlikBilgisi(Context context) {
        this.context = context;

    }

    public fragmentKimlikBilgisi(Context context, Ogrenci ogrenci) {
        this.context = context;
        setOgrenci(ogrenci);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragmen_kimlik_bilgisi,container,false);
        //View view1 = inflater.inflate(R.layout.nav_header_ogrenci,container,false);
        adSoyad = view.findViewById(R.id.OgrenciAdSoyad);
        email = view.findViewById(R.id.OgrenciEposta);
        tcNo = view.findViewById(R.id.ogrenci_tc_kimlikNo);
        sinif = view.findViewById(R.id.OgrenciSinif);
        resim = view.findViewById(R.id.ogrenciFotograf);
        storageReference = FirebaseStorage.getInstance().getReference();
        parolaSifirla=view.findViewById(R.id.btnParolaSifirla);
        mainActivity=new MainActivity();
        bilgiGoster();

        parolaSifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("PARALO SIFIRLAMA");
                alert.setMessage(email.getText().toString()+" EMAIL ADRESINIZE GONDERILSIN MI?");
                alert.setPositiveButton("GONDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //mail adresini alert dialogdaki edittextten alacak

                        mainActivity.mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Yeni parola için gerekli bağlantı adresinize gönderildi!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Mail gönderme hatası!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                alert.setNegativeButton("IPTAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();

            }
        });


        return view;
    }


    public void bilgiGoster(){
        if(ogretmen!= null){
            adSoyad.setText(ogretmen.getAdSoyad());
            tcNo.setText(ogretmen.gettCNo());
            email.setText(ogretmen.getEmailAdres());

        }
        else if(ogrenci!= null){
            System.out.println("--------------------ögrenci içindeyiz");
            adSoyad.setText(ogrenci.getAdSoyad());
            tcNo.setText(ogrenci.gettCNo());
            email.setText(ogrenci.getEmailAdres());
            sinif.setText(ogrenci.getClassNumber());

            //resim.setImageBitmap(resim1);
//            try {
//                localFile = File.createTempFile("resim","jpg");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            StorageReference ref = storageReference.child("pht_"+ogrenci.gettCNo());
//            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    Bitmap res = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                    resim.setImageBitmap(res);
//                }
//            });
            Picasso.with(context).load(Uri.parse(ogrenci.getResim())).into(resim);

        }
        else if(veli!= null){
            adSoyad.setText(veli.getAdSoyad());
            tcNo.setText(veli.gettCNo());
            email.setText(veli.getEmailAdres());

        }
    }

    public Ogretmen getOgretmen() {
        return ogretmen;
    }

    public void setOgretmen(Ogretmen ogretmen) {
        this.ogretmen = ogretmen;
    }

    public Ogrenci getOgrenci() {
        return ogrenci;
    }

    public void setOgrenci(Ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }

    public Veli getVeli() {
        return veli;
    }

    public void setVeli(Veli veli) {
        this.veli = veli;
    }












}
