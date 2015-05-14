package evertonrmachado.gclientes.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import evertonrmachado.gclientes.ClienteCadastroActivity;
import evertonrmachado.gclientes.Util.ClienteHelper;
import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 04/04/2015.
 */
public class ClienteCadAltBtnFrag extends Fragment {

    @InjectView(R.id.cliente_alterar_btn_calcelar) Button btnCancelar;
    @InjectView(R.id.cliente_alterar_btn_salvar) Button btnSalvar;

    private ClienteHelper clienteHelper;
    private ClienteDAO clienteDAO;
    private Cliente clienteCadAlt;
    private int idClienteAlt;

    public ClienteCadAltBtnFrag newInstance(Cliente cliente){
        ClienteCadAltBtnFrag clienteCadAltBtnFrag = new ClienteCadAltBtnFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);
        clienteCadAltBtnFrag.setArguments(bundle);
        return clienteCadAltBtnFrag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cliente_cad_alt_btns_new, container, false);

        ButterKnife.inject(this, view);

        clienteCadAlt = (Cliente) getArguments().getSerializable("cliente");

        if(clienteCadAlt != null){
            btnSalvar.setText("Alterar");
            idClienteAlt = clienteCadAlt.getIdCliente();
        }
        else
            btnSalvar.setText("Salvar");


        clienteHelper = new ClienteHelper(getActivity());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Operação Cancelada", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clienteCadAlt = clienteHelper.pegarClienteFormulario();
                clienteDAO = new ClienteDAO(getActivity());
                String msg = "";
                AlertDialog alerta;


                if (!clienteCadAlt.getNome().equals("") && (!clienteCadAlt.getCelular().equals("") || !clienteCadAlt.getTelefone().equals("") || !clienteCadAlt.getEmail().equals(""))){

                    if(btnSalvar.getText() == "Salvar"){
                        clienteDAO.insert(clienteCadAlt);
                        clienteDAO.close();

                        Toast.makeText(getActivity(), "Cliente Salvo com Sucesso", Toast.LENGTH_LONG).show();

                        getActivity().finish();


                    }
                    else if (btnSalvar.getText() == "Alterar"){
                        clienteCadAlt.setIdCliente(idClienteAlt);
                        clienteDAO.update(clienteCadAlt);
                        clienteDAO.close();

                        Toast.makeText(getActivity(), "Cliente Alterado com Sucesso", Toast.LENGTH_LONG).show();

                        getActivity().finish();
                    }

                }
                else{
                    if(clienteCadAlt.getNome().equals("")){
                        msg += "Nome" + "\n";
                    }
                    if(clienteCadAlt.getCelular().equals("") && clienteCadAlt.getTelefone().equals("") && clienteCadAlt.getEmail().equals("")){
                        msg += "Contato: Telefone, Celular ou E-mail" + "\n";
                    }


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Campo(s) Obrigatorio(s)");
                    builder.setMessage(msg);
                    builder.setPositiveButton("Ok", null);
                    alerta = builder.create();
                    alerta.show();
                    msg="";
                }

            }
        });



    }
}
