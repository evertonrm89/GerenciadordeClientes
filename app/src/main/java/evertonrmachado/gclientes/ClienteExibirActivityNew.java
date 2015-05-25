package evertonrmachado.gclientes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import evertonrmachado.gclientes.dao.ClienteDAO;
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
    @InjectView(R.id.cliente_exibir_btnFavorito) ImageButton btnFavorito;

    private Cliente exibirCliente;
    private int idCliente;
    private ClienteDAO clienteDAO;
    private AlertDialog alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_exibir_new);

        Intent intent = getIntent();

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

        if (exibirCliente.getFavorito() == 1){
            btnFavorito.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else
            btnFavorito.setImageResource(android.R.drawable.btn_star_big_off);

        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDAO clienteDAO = new ClienteDAO(ClienteExibirActivityNew.this);

                if (exibirCliente.getFavorito() == 0) {
                    clienteDAO.setFavorito(exibirCliente.getIdCliente(), 1);
                    exibirCliente.setFavorito(1);
                    btnFavorito.setImageResource(android.R.drawable.btn_star_big_on);

                } else {
                    clienteDAO.setFavorito(exibirCliente.getIdCliente(), 0);
                    exibirCliente.setFavorito(0);
                    btnFavorito.setImageResource(android.R.drawable.btn_star_big_off);
                }

                clienteDAO.close();
            }
        });

        if(exibirCliente.getTelefone().isEmpty()){
            btnTelefone.setEnabled(false);
        }
        else{
            btnTelefone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + exibirCliente.getTelefone()));
                    startActivity(intentLigar);
                }
            });
        }
        if(exibirCliente.getCelular().isEmpty()){
            btnCelular.setEnabled(false);
        }
        else{
            btnCelular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + exibirCliente.getCelular().trim()));
                    startActivity(intentLigar);
                }

            });
        }

        if(exibirCliente.getEmail().isEmpty()){
            btnEmail.setEnabled(false);
        }
        else {
            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intentEmail = new Intent(Intent.ACTION_SEND);
                    intentEmail.setType("message/rtc822");
                    intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{exibirCliente.getEmail()});
                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "");
                    intentEmail.putExtra(Intent.EXTRA_TEXT, "\n Enviado via app: GClientes (Gerenciador de Clientes)");

                    try {
                        startActivity(intentEmail);
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(ClienteExibirActivityNew.this, "Por favor, verifique o aplicativo de E-mail", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        if(exibirCliente.getEndereco().isEmpty()){
            btnMap.setEnabled(false);
        }
        else {
            btnMap.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    String endereco = exibirCliente.getEndereco();
                    if(!exibirCliente.getCidade().isEmpty()){endereco += ", " + exibirCliente.getCidade();}
                    if(!exibirCliente.getEstado().isEmpty()){endereco += ", " + exibirCliente.getEstado();}

                    String uri = String.format(Locale.getDefault(), "geo:0,0?z=14&q=" + endereco);

                    //String uri = String.format(Locale.getDefault(), "geo:%f,%f?q=%f,%f (%s)" + endereco);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                   // intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        try {
                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            startActivity(unrestrictedIntent);
                        } catch (ActivityNotFoundException innerEx) {
                            Toast.makeText(ClienteExibirActivityNew.this, "Por favor, verifique o aplicativo de mapa", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
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

                AlertDialog alerta;
                AlertDialog.Builder builder = new AlertDialog.Builder(ClienteExibirActivityNew.this);
                builder.setTitle(exibirCliente.getNome());
                builder.setMessage("Deseja realmente excluir esse Cliente?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClienteDAO clienteDAO = new ClienteDAO(ClienteExibirActivityNew.this);
                        clienteDAO.delete(exibirCliente);
                        clienteDAO.close();

                        Toast.makeText(ClienteExibirActivityNew.this, "Cliente " + exibirCliente.getNome() + ", excluido com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                alerta = builder.create();
                alerta.show();


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

        if (exibirCliente.getFavorito() == 1){
            btnFavorito.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else
            btnFavorito.setImageResource(android.R.drawable.btn_star_big_off);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
