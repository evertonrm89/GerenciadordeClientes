package evertonrmachado.gclientes.Util;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 25/02/2015.
 */
public class ClienteHelper {

    private TextView txtNome;
    private TextView txtTelefone;
    private TextView txtCelular;
    private TextView txtEmail;
    private TextView txtEndereco;
    private TextView txtCidade;
    private TextView txtEstado;
    private TextView txtObservacao;
    private ToggleButton chkStatus;
    private CheckBox chkFavorito;

    public Cliente cliente;


    public ClienteHelper(Activity activity) {

        txtNome = (TextView) activity.findViewById(R.id.cliente_cadalt_nome);
        txtTelefone = (TextView) activity.findViewById(R.id.cliente_cadalt_telefone);
        txtCelular = (TextView) activity.findViewById(R.id.cliente_cadalt_celular);
        txtEmail = (TextView) activity.findViewById(R.id.cliente_cadalt_email);
        txtEndereco = (TextView) activity.findViewById(R.id.cliente_cadalt_endereco);
        txtCidade = (TextView) activity.findViewById(R.id.cliente_cadalt_cidade);
        txtEstado = (TextView) activity.findViewById(R.id.cliente_cadalt_estado);
        txtObservacao = (TextView) activity.findViewById(R.id.cliente_cadalt_observacao);
        chkStatus = (ToggleButton) activity.findViewById(R.id.cliente_cadalt_status);
        chkFavorito = (CheckBox) activity.findViewById(R.id.cliente_cadalt_favorito);

        cliente = new Cliente();


    }

    public Cliente pegarClienteFormulario(){

        cliente.setNome(txtNome.getEditableText().toString());
        cliente.setTelefone(txtTelefone.getEditableText().toString());
        cliente.setCelular(txtCelular.getEditableText().toString());
        cliente.setEmail(txtEmail.getEditableText().toString());
        cliente.setEndereco(txtEndereco.getEditableText().toString());
        cliente.setCidade(txtCidade.getEditableText().toString());
        cliente.setEstado(txtEstado.getEditableText().toString());
        cliente.setObservacao(txtObservacao.getEditableText().toString());

        boolean isCheckedS = chkStatus.isChecked();

        if (isCheckedS){
            cliente.setStatus(1);
        }
        else{
            cliente.setStatus(0);
        }

        boolean isCheckedF = chkFavorito.isChecked();

        if (isCheckedF){
            cliente.setFavorito(1);
        }
        else{
            cliente.setFavorito(0);
        }

        return cliente;
    }

    public void carregarClienteFormulario(Cliente cliente){

        this.cliente = cliente;

        txtNome.setText(this.cliente.getNome());
        txtTelefone.setText(this.cliente.getTelefone());
        txtCelular.setText(this.cliente.getCelular());
        txtEmail.setText(this.cliente.getEmail());
        txtEndereco.setText(this.cliente.getEndereco());
        txtCidade.setText(this.cliente.getCidade());
        txtEstado.setText(this.cliente.getEstado());
        txtObservacao.setText(this.cliente.getObservacao());

        if (this.cliente.getFavorito() == 1){
            chkFavorito.setChecked(true);
        }
        else {
            chkFavorito.setChecked(false);
        }

        if (this.cliente.getStatus() == 1){
            chkStatus.setChecked(true);
        }
        else{
            chkStatus.setChecked(false);
        }

    }




}
