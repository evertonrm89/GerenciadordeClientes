package evertonrmachado.gclientes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;
import java.util.ResourceBundle;

import evertonrmachado.gclientes.dao.ClienteDAO;
import evertonrmachado.gerenciadordeclientes.R;
import evertonrmachado.gclientes.modelo.Cliente;

/**
 * Created by Everton on 24/02/2015.
 */
public class ClienteAdaptar extends RecyclerView.Adapter<ClienteAdaptar.ClienteViewHolder> {

    private List<Cliente> clientes;
    private LayoutInflater layoutInflater;

    private Context ctx;
    private View view;

    private Cliente cliente;

    OnItemClickListener mItemClickListener;

    public ClienteAdaptar(List<Cliente> clientes, Context ctx, int status) {
        this.clientes = clientes;
        this.ctx = ctx;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public void addListItem(Cliente c, int position){
        clientes.add(c);
        notifyItemInserted(position);
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = layoutInflater.from(ctx).inflate(R.layout.cliente_lista_item, parent, false);
        ClienteViewHolder holder = new ClienteViewHolder(view);
        return holder;
    }


    public void onBindViewHolder(final ClienteViewHolder holder, int position) {

        cliente = clientes.get(position);
        String contatosPhone = null;
        String contatoEmail = null;
        String contato = null;

        if(!cliente.getTelefone().equals("")){
            if (!cliente.getCelular().equals("")){
                contatosPhone = "Tel: "+cliente.getTelefone() +" / Cel: "+cliente.getCelular();
            }
            else {
                contatosPhone = "Tel: "+cliente.getTelefone();
            }
        }
        else if (!cliente.getCelular().equals("")){
            contatosPhone = "Cel: "+cliente.getCelular();
        }
        if (!cliente.getEmail().equals("")){
            contatoEmail = cliente.getEmail();
        }
        holder.txtNome.setText(cliente.getNome());

        if (contatosPhone != null && contatoEmail == null){
            contato = contatosPhone;
        }
        if (contatosPhone == null && contatoEmail != null){
            contato = contatoEmail;
        }
        if (contatosPhone != null && contatoEmail != null){
            contato = contatosPhone +"\n"+ contatoEmail;
        }

        holder.txtContato.setText(contato);

        if (cliente.getFavorito() == 1){
            holder.imgFavorito.setImageResource(android.R.drawable.btn_star_big_on);
        }
        else
            holder.imgFavorito.setImageResource(android.R.drawable.btn_star_big_off);



        /*holder.chkFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDAO clienteDAO = new ClienteDAO(ctx);

                if(holder.chkFavorito.isChecked()){
                    clienteDAO.setFavorito(cliente.getIdCliente(), 1);
                    cliente.setFavorito(1);
                    holder.chkFavorito.setChecked(true);
                    notifyItemChanged(holder.getPosition());

                }
                else {
                    clienteDAO.setFavorito(cliente.getIdCliente(), 0);
                    cliente.setFavorito(0);
                    holder.chkFavorito.setChecked(false);
                    notifyItemChanged(holder.getPosition());

                }

                clienteDAO.close();
            }
        });*/

        try {
            YoYo.with(Techniques.Landing)
                    .duration(500)
                    .playOn(holder.itemView);
        }
        catch (Exception e){

        }

    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtContato;
        public TextView txtNome;
        public ImageView imgFavorito;

        public ClienteViewHolder(final View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.cliente_lista_item_nome);
            txtContato = (TextView) itemView.findViewById(R.id.cliente_lista_item_contato);
            imgFavorito = (ImageView) itemView.findViewById(R.id.cliente_lista_item_imgview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (mItemClickListener != null){
                mItemClickListener.onItemClick(v, getPosition());
            }
        }

    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}
