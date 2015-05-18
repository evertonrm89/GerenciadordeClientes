package evertonrmachado.gclientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;
import evertonrmachado.gclientes.fragment.ClienteListaFrag;
import evertonrmachado.gerenciadordeclientes.R;


public class MainActivity extends NavigationLiveo implements NavigationLiveoListener {

    public List<String> mListNameItem;
    public List<Integer> mListHeaderItem;
    public List<Integer> mListIconItem;

    @Override
    public void onUserInformation() {

        //User information here
        this.mUserName.setText("");
        this.mUserEmail.setText("");
        //this.mUserPhoto.setImageResource(R.drawable.ic_action_user);
        this.mUserBackground.setImageResource(R.drawable.fundo2);

    }

    @Override
    public void onInt(Bundle bundle) {

        this.setNavigationListener(this);

        this.setDefaultStartPositionNavigation(1);

        // name of the list items
        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.cliente));
        mListNameItem.add(1, getString(R.string.favorito));
        mListNameItem.add(2, getString(R.string.cliente_ativo));
        mListNameItem.add(3, getString(R.string.cliente_inativo));
        mListNameItem.add(4, getString(R.string.vazio));
        mListNameItem.add(5, getString(R.string.backup));
        //mListNameItem.add(3, getString(R.string.agenda));
        //mListNameItem.add(4, getString(R.string.clientes_proximos));
       // mListNameItem.add(5, getString(R.string.relatorios));


        // icons list items
        mListIconItem = new ArrayList<>();
        mListIconItem.add(0, 0); //Item no icon set 0
        mListIconItem.add(1, R.drawable.account_star);
        mListIconItem.add(2, R.drawable.account);
        mListIconItem.add(3, R.drawable.account_remove);
        mListIconItem.add(4, 0);
        mListIconItem.add(5, R.drawable.ic_backup);
        //mListIconItem.add(3, R.drawable.ic_action_calendar_month); //Item no icon set 0
        //mListIconItem.add(4, R.drawable.ic_action_globe);
        //mListIconItem.add(5, R.drawable.ic_action_line_chart);

        mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(0);
        mListHeaderItem.add(4);

        // {} Opcional - Entre os nomes houver qualquer contador de itens, você deve indicá-lo (posição) eo valor aqui
       // SparseIntArray mSparseCounterItem = new SparseIntArray (); // indicar todos os itens que têm um contador
       // mSparseCounterItem.put(0, 7);
       // mSparseCounterItem.put(6, 250);

        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, null);

    }

   @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {

       FragmentManager mFragmentManager = getSupportFragmentManager();

       ClienteListaFrag clienteListaFrag;

       this.getToolbar().setBackgroundColor(getResources().getColor(R.color.nliveo_blue_colorPrimary));

       switch (position){

           case 1:
               clienteListaFrag = new ClienteListaFrag().newInstance(1);
               mFragmentManager.beginTransaction().replace(layoutContainerId, clienteListaFrag).commit();
               break;
           case 2:
               clienteListaFrag = new ClienteListaFrag().newInstance(0);
               mFragmentManager.beginTransaction().replace(layoutContainerId, clienteListaFrag).commit();
               break;

           case 0:
               clienteListaFrag = new ClienteListaFrag().newInstance(3);
               mFragmentManager.beginTransaction().replace(layoutContainerId, clienteListaFrag).commit();
               break;
       }
    }

    @Override
    protected void onResume() {

        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = this.getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        menu.findItem(R.id.menu_add).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idSelecionado = item.getItemId();

        switch (idSelecionado) {
            case R.id.menu_add:
                Intent intent = new Intent(this, ClienteCadastroActivity.class);
                startActivity(intent);

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int i, boolean b) {


    }

    @Override
    public void onClickFooterItemNavigation(View view) {

    }

    @Override
    public void onClickUserPhotoNavigation(View view) {

    }


}
