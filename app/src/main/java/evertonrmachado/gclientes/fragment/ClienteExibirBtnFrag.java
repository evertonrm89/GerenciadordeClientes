package evertonrmachado.gclientes.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 30/04/2015.
 */
public class ClienteExibirBtnFrag extends Fragment {

    @InjectView(R.id.cliente_exibir_btnligar) ImageButton btnLigar;
    @InjectView(R.id.cliente_exibir_btnemail) ImageButton btnEmail;
    @InjectView(R.id.cliente_exibir_btngps) ImageButton btnMap;
    @InjectView(R.id.cliente_exibir_btndeletar) ImageButton btnDeletar;

    public Cliente cliente;
    private FragmentActivity mActivity;


    public ClienteExibirBtnFrag newInstance(Cliente cliente){
        ClienteExibirBtnFrag clienteExibirBtnFrag = new ClienteExibirBtnFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);
        clienteExibirBtnFrag.setArguments(bundle);
        return clienteExibirBtnFrag;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cliente_exibir_btns, container, false);

        ButterKnife.inject(this, view);

        cliente = (Cliente) getArguments().getSerializable("cliente");

        //Context ctx = getActivity().getBaseContext();

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLigar = new Intent(Intent.ACTION_CALL);
                intentLigar.setData(Uri.parse("tel:" + cliente.getCelular()));
                startActivity(intentLigar);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setType("message/rtc822");
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {""});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(intentEmail);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + cliente.getEndereco()));
                startActivity(intentMapa);
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDAO clienteDAO = new ClienteDAO(mActivity);
                clienteDAO.delete(cliente);
                clienteDAO.close();

                getActivity().finish();
            }
        });


        return view;
    }

}
