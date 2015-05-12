package evertonrmachado.gclientes.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ToggleButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 19/03/2015.
 */
public class ClienteCadAltFrag extends Fragment {

    @InjectView(R.id.cliente_cadalt_nome) EditText txtNome;
    @InjectView(R.id.cliente_cadalt_telefone) EditText txtTelefone;
    @InjectView(R.id.cliente_cadalt_celular) EditText txtCelular;
    @InjectView(R.id.cliente_cadalt_email) EditText txtEmail;
    @InjectView(R.id.cliente_cadalt_endereco) EditText txtEndereco;
    @InjectView(R.id.cliente_cadalt_cidade) EditText txtCidade;
    @InjectView(R.id.cliente_cadalt_estado) EditText txtEstado;
    @InjectView(R.id.cliente_cadalt_observacao) EditText txtObservacao;
    @InjectView(R.id.cliente_cadalt_status) ToggleButton chkStatus;
    @InjectView(R.id.cliente_cadalt_favorito) CheckBox chkFavorito;


    private FragmentActivity mActivity;
    private Cliente clienteCadAlt;

    public ClienteCadAltFrag newInstance(Cliente cliente){
        ClienteCadAltFrag clienteCadAltFrag = new ClienteCadAltFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);
        clienteCadAltFrag.setArguments(bundle);
        return clienteCadAltFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cliente_cad_alt, container, false);

        ButterKnife.inject(this, view);

        clienteCadAlt = (Cliente) getArguments().getSerializable("cliente");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (clienteCadAlt != null){

            txtNome.setText(clienteCadAlt.getNome());

            txtTelefone.setText(clienteCadAlt.getTelefone());

            txtCelular.setText(clienteCadAlt.getCelular());

            txtEmail.setText(clienteCadAlt.getEmail());

            txtEndereco.setText(clienteCadAlt.getEndereco());

            txtCidade.setText(clienteCadAlt.getCidade());

            txtEstado.setText(clienteCadAlt.getEstado());

            txtObservacao.setText(clienteCadAlt.getObservacao());

            if(clienteCadAlt.getFavorito() == 1){
                chkFavorito.setChecked(true);
            }
            else
                chkFavorito.setChecked(false);

            if(clienteCadAlt.getStatus() == 1){
                chkStatus.setChecked(true);
            }
            else
                chkStatus.setChecked(false);
        }


    }
}
