package evertonrmachado.gclientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import evertonrmachado.gclientes.fragment.ClienteCadAltBtnFrag;
import evertonrmachado.gclientes.fragment.ClienteCadAltFrag;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 25/02/2015.
 */
public class ClienteCadastroActivity extends Activity {

    private ClienteCadAltFrag clienteCadAltFrag;
    private ClienteCadAltBtnFrag clienteCadAltBtnFrag;
    private android.app.FragmentManager mFragmentManager;
    private Cliente editarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_fragment);

        Intent intent = getIntent();

        editarCliente = (Cliente) intent.getSerializableExtra("exibirCliente");

        mFragmentManager = getFragmentManager();

        clienteCadAltFrag = new ClienteCadAltFrag().newInstance(editarCliente);
        clienteCadAltBtnFrag = new ClienteCadAltBtnFrag().newInstance(editarCliente);

        mFragmentManager.beginTransaction().replace(R.id.cliente_fragment_dados, clienteCadAltFrag).commit();

        mFragmentManager.beginTransaction().replace(R.id.cliente_fragment_btns, clienteCadAltBtnFrag).commit();

    }


}
