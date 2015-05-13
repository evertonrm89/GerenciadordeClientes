package evertonrmachado.gclientes.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import evertonrmachado.gclientes.ClienteExibirActivity;
import evertonrmachado.gclientes.ClienteExibirActivityNew;
import evertonrmachado.gerenciadordeclientes.R;
import evertonrmachado.gclientes.adapter.ClienteAdaptar;
import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gclientes.modelo.Cliente;

/**
 * Created by Everton on 25/02/2015.
 */
public class ClienteListaFrag extends Fragment {

    private View layout;
    private Context ctx;
    private RecyclerView listaClientes;
    private List<Cliente> clientes;
    private ClienteAdaptar adaptar;
    private int status;

    private LinearLayoutManager linearLayoutManager;
    FragmentActivity mActivity;

    public void setStatus(int status){

        this.status = status;
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

        ctx = getActivity().getBaseContext();

        layout = inflater.inflate(R.layout.cliente_lista, container, false);

        listaClientes = (RecyclerView) layout.findViewById(R.id.cliente_lista_recycler);
        listaClientes.setHasFixedSize(true);
        listaClientes.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        criaAdapter(status);

        adaptar.SetOnItemClickListener(new ClienteAdaptar.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                int idCliente = clientes.get(position).getIdCliente();
                Bundle bundle = new Bundle();
                bundle.putInt("idCliente", idCliente);

                Intent intent = new Intent(ctx, ClienteExibirActivityNew.class);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });

        /*listaClientes.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) listaClientes.getLayoutManager();
                ClienteAdaptar clienteAdaptar = (ClienteAdaptar) listaClientes.getAdapter();

                if(clientes.size() == llm.findLastCompletelyVisibleItemPosition() + 1){

                   // List<Cliente> clienteaux = getActivity

                }

            }
        });*/

        return layout;
    }

  //  private int contCliente=0;

    /*public List<Cliente> geraSeisClientes(List<Cliente> clientesBanco){

        List<Cliente> clientes6 = new ArrayList<Cliente>();

        int i, cont;

        if (clientesBanco.size() < 6){
            cont = clientesBanco.size();
        }
        else {
            cont = 6;
        }

        for ( i = contCliente; i <= (contCliente+cont); i++ ){

            clientes6.add(clientesBanco.get(i));
        }

        contCliente = i;

        return clientes6;
    }*/

    public void adapterAlter (int status){

        if (adaptar == null){
            adaptar = new ClienteAdaptar(clientes, mActivity, status);
            listaClientes.setAdapter(adaptar);
        }
        else{
            adaptar.notifyDataSetChanged();
        }

    }

    public void gerarListaClientesDAO (int status) {

        ClienteDAO dao = new ClienteDAO(ctx);
        clientes = dao.getListaClientesStatusFavorito(status);
        dao.close();
    }

    public void criaAdapter(int status){

        gerarListaClientesDAO(status);

        adaptar = new ClienteAdaptar(clientes, mActivity, status);

        listaClientes.setAdapter(adaptar);

        listaClientes.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onResume() {
        super.onResume();

        //criaAdapter(status);

    }
}
