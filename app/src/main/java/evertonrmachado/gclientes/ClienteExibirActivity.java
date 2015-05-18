package evertonrmachado.gclientes;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;


import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gclientes.fragment.ClienteExibirFrag;
import evertonrmachado.gclientes.modelo.Cliente;
import evertonrmachado.gerenciadordeclientes.R;

/**
 * Created by Everton on 04/03/2015.
 */
public class ClienteExibirActivity extends Activity {

    private ClienteExibirFrag clienteExibirFrag;
    private android.app.FragmentManager mFragmentManager;
    private Cliente exibirCliente;
    private int idCliente;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_fragment);

        mFragmentManager = getFragmentManager();

        ClienteDAO clienteDAO = new ClienteDAO(this);

        intent = getIntent();
        idCliente = intent.getExtras().getInt("idCliente");

        exibirCliente = clienteDAO.getClienteId(idCliente);

        clienteDAO.close();

        if (clienteExibirFrag == null ){

            clienteExibirFrag = new ClienteExibirFrag().newInstance(exibirCliente);
            mFragmentManager.beginTransaction().replace(R.id.cliente_fragment_dados, clienteExibirFrag).commit();

        }

        //tituloActionBar = "Código do Cliente: " + exibirCliente.getIdCliente();

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle(tituloActionBar);

        /*final View activityRootView = findViewById(R.id.cliente_fragment);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

           //--->
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > 100 && clienteCadAltFrag != null) {
                    Toast.makeText(ClienteExibirActivity.this, "A", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ClienteExibirActivity.this, "D", Toast.LENGTH_SHORT).show();
                }

           //--->
               /* boolean isKeyboardActive = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).isActive();
                if (isKeyboardActive == true) {
                    Toast.makeText(ClienteExibirActivity.this, "A", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ClienteExibirActivity.this, "Desapareceu", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle(tituloActionBar);
    }



   /* public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = this.getMenuInflater();

        inflater.inflate(R.menu.menu_exibir, menu);

        menu.findItem(R.id.menu_edit).setVisible(true);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idSelecionado = item.getItemId();
        ClienteDAO clienteDAO = new ClienteDAO(this);

        switch (idSelecionado) {
            case R.id.menu_edit:

                exibirCliente = clienteDAO.getClienteId(idCliente);
                clienteDAO.close();

                if (clienteCadAltFrag == null && clienteCadAltBtnFrag == null) {
                    clienteCadAltFrag = new ClienteCadAltFrag().newInstance(exibirCliente);
                    mFragmentManager.beginTransaction().replace(R.id.cliente_fragment_dados, clienteCadAltFrag).commit();

                    //clienteCadAltBtnFrag = new ClienteCadAltBtnFrag().newInstance(exibirCliente);
                    //mFragmentManager.beginTransaction().replace(R.id.cliente_fragment_btns, clienteCadAltBtnFrag).commit();

                    item.setVisible(false);
                }

                return false;

            default:
                finish();
                return super.onOptionsItemSelected(item);
        }

    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }*/
}