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


    public ClienteListaFrag newInstance(int status){
        ClienteListaFrag clienteListaFrag = new ClienteListaFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("status", status);
        clienteListaFrag.setArguments(bundle);

        return clienteListaFrag;
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

        status = getArguments().getInt("status");

        ctx = getActivity().getBaseContext();

        layout = inflater.inflate(R.layout.cliente_lista, container, false);

        listaClientes = (RecyclerView) layout.findViewById(R.id.cliente_lista_recycler);

        criarLista(status);

        return layout;

    }

    public void criarLista(int status){

        clientes = gerarListaClientesDAO(status);

        listaClientes.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(mActivity);
        listaClientes.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adaptar = new ClienteAdaptar(clientes, mActivity, status);

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

        listaClientes.setAdapter(adaptar);
    }

    public List<Cliente> gerarListaClientesDAO (int status) {

        List<Cliente> clientesBanco = new ArrayList<Cliente>();

        ClienteDAO dao = new ClienteDAO(ctx);
        clientesBanco = dao.getListaClientesStatusFavorito(status);
        dao.close();

        return clientesBanco;
    }

    @Override
    public void onResume() {
        super.onResume();
        criarLista(status);
    }
}
