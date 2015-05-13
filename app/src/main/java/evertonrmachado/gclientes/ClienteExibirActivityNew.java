package evertonrmachado.gclientes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gclientes.fragment.ClienteCadAltBtnFrag;
import evertonrmachado.gclientes.fragment.ClienteCadAltFrag;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 13/05/2015.
 */
public class ClienteExibirActivityNew extends Activity {

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
    @InjectView(R.id.cliente_exibir_btnDeletar) ImageButton btnDeletar;
    @InjectView(R.id.cliente_exibir_btnEditar) ImageButton btnEditar;
    @InjectView(R.id.cliente_exibir_btnhome) ImageButton btnHome;

    private Cliente exibirCliente;
    private int idCliente;
    private ClienteDAO clienteDAO;
    private ClienteCadAltFrag clienteCadAltFrag;
    private ClienteCadAltBtnFrag clienteCadAltBtnFrag;
    private android.app.FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_exibir_new);

        Intent intent = getIntent();

        mFragmentManager = getFragmentManager();

        idCliente = intent.getExtras().getInt("idCliente");

        clienteDAO = new ClienteDAO(this);
        exibirCliente = clienteDAO.getClienteId(idCliente);
        clienteDAO.close();

        ButterKnife.inject(this);

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
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{exibirCliente.getEmail()});
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

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("exibirCliente", exibirCliente);
                Intent intent = new Intent(ClienteExibirActivityNew.this, ClienteCadastroActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDAO clienteDAO = new ClienteDAO(ClienteExibirActivityNew.this);
                clienteDAO.delete(exibirCliente);
                clienteDAO.close();

                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        clienteDAO = new ClienteDAO(this);
        exibirCliente = clienteDAO.getClienteId(idCliente);
        clienteDAO.close();

        txtNome.setText(exibirCliente.getNome());
        txtTelefone.setText(exibirCliente.getTelefone());
        txtCelular.setText(exibirCliente.getCelular());
        txtEmail.setText(exibirCliente.getEmail());
        txtEndereco.setText(exibirCliente.getEndereco());
        txtCidade.setText(exibirCliente.getCidade());
        txtEstado.setText(exibirCliente.getEstado());
        txtObservacao.setText(exibirCliente.getObservacao());


    }
}
