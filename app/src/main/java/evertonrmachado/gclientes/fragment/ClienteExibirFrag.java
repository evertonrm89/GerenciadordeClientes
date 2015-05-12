package evertonrmachado.gclientes.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 17/03/2015.
 */
public class ClienteExibirFrag extends Fragment {

    @InjectView(R.id.cliente_exibir_nome) TextView txtNome;
    @InjectView(R.id.cliente_exibir_telefone) TextView txtTelefone;
    @InjectView(R.id.cliente_exibir_celular) TextView txtCelular;
    @InjectView(R.id.cliente_exibir_email) TextView txtEmail;
    @InjectView(R.id.cliente_exibir_endereco) TextView txtEndereco;
    @InjectView(R.id.cliente_exibir_cidade) TextView txtCidade;
    @InjectView(R.id.cliente_exibir_estado) TextView txtEstado;
    @InjectView(R.id.cliente_exibir_observacao) TextView txtObservacao;
    @InjectView(R.id.cliente_exibir_btnTelefone) ImageButton btnTelefone;
    @InjectView(R.id.cliente_exibir_btnCelular) ImageButton btnCelular;
    @InjectView(R.id.cliente_exibir_btnemail) ImageButton btnEmail;
    @InjectView(R.id.cliente_exibir_btngps) ImageButton btnMap;
    //@InjectView(R.id.cliente_exibir_btndeletar) ImageButton btnDeletar;

    public Cliente exibirCliente;

    //private FragmentActivity mActivity;


    public ClienteExibirFrag newInstance(Cliente cliente){
        ClienteExibirFrag clienteExibirFrag = new ClienteExibirFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);
        clienteExibirFrag.setArguments(bundle);
        return clienteExibirFrag;
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
        setRetainInstance(true);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cliente_exibir_new, container, false);

        exibirCliente = (Cliente) getArguments().getSerializable("cliente");

        ButterKnife.inject(this, view);

        txtNome.setText(exibirCliente.getNome());
        txtTelefone.setText(exibirCliente.getTelefone());
        txtCelular.setText(exibirCliente.getCelular());
        txtEmail.setText(exibirCliente.getEmail());
        txtEndereco.setText(exibirCliente.getEndereco());
        txtCidade.setText(exibirCliente.getCidade());
        txtEstado.setText(exibirCliente.getEstado());
        txtObservacao.setText(exibirCliente.getObservacao());

        btnTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLigar = new Intent(Intent.ACTION_CALL);
                intentLigar.setData(Uri.parse("tel:" + exibirCliente.getTelefone()));
                startActivity(intentLigar);
            }
        });

        btnCelular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLigar = new Intent(Intent.ACTION_CALL);
                intentLigar.setData(Uri.parse("tel:" + exibirCliente.getCelular()));
                startActivity(intentLigar);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setType("message/rtc822");
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(intentEmail);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + exibirCliente.getEndereco()));
                startActivity(intentMapa);
            }
        });

        /*btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDAO clienteDAO = new ClienteDAO(mActivity);
                clienteDAO.delete(exibirCliente);
                clienteDAO.close();

                getActivity().finish();
            }
        });*/

        return view;
    }

}
