package evertonrmachado.gclientes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import evertonrmachado.gclientes.modelo.Cliente;

/**
 * Created by Everton on 24/02/2015.
 */
public class ClienteDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA_CLIENTES = "clientes";
    static Context context;
    private static final String DATABASE = "GClientes";
    private static final String[] COLUNAS_CLIENTES = { "idCliente", "nome", "telefone", "celular", "email",
            "endereco", "cidade", "estado", "foto", "observacao", "status", "favorito" };

    private static final String FILTER_STATUS = "status = ?";
    private static final String FILTER_FAVORITO = "favorito = ?";
    private static final String FILTER_ID = "idCliente = ?";

    public ClienteDAO(Context context) {
        super(context, DATABASE, null, VERSAO);

        this.context = context;
    }

    @Override
    //Cria a Tabela no banco de dados
    public void onCreate(SQLiteDatabase db)  {

        String comando = "CREATE TABLE "+ TABELA_CLIENTES +" ([idCliente] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [nome] VARCHAR, " +
                "[telefone] VARCHAR, [celular] VARCHAR, [email] VARCHAR, [endereco] VARCHAR, [cidade] VARCHAR, " +
                "[estado] VARCHAR, [foto] VARCHAR2, [observacao] VARCHAR2, [status] INTEGER, [favorito] INTEGER);";

        db.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_CLIENTES + ";";

        db.execSQL(sql);

        onCreate(db);
    }

    public void setFavorito(int idCliente, int statusFavorito){

        ContentValues values = new ContentValues();
        String[] args = {String.valueOf(idCliente)};


        values.put("favorito", statusFavorito);

        getWritableDatabase().update(TABELA_CLIENTES, values, "idCliente=?", args);


    }

    public void insert (Cliente cliente){

        ContentValues values = new ContentValues();

        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("celular", cliente.getCelular());
        values.put("email", cliente.getEmail());
        values.put("endereco", cliente.getEndereco());
        values.put("cidade", cliente.getCidade());
        values.put("estado", cliente.getEstado());
        values.put("foto", "");
        values.put("observacao", cliente.getObservacao());
        values.put("status", cliente.getStatus());
        values.put("favorito", cliente.getFavorito());

        getWritableDatabase().insert(TABELA_CLIENTES, null, values);

    }

    public void update(Cliente cliente){

        ContentValues values = new ContentValues();
        String[] args = {String.valueOf(cliente.getIdCliente())};

        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("celular", cliente.getCelular());
        values.put("email", cliente.getEmail());
        values.put("endereco", cliente.getEndereco());
        values.put("cidade", cliente.getCidade());
        values.put("estado", cliente.getEstado());
        //values.put("foto", "");
        values.put("observacao", cliente.getObservacao());
        values.put("status", cliente.getStatus());
        values.put("favorito", cliente.getFavorito());

        getWritableDatabase().update(TABELA_CLIENTES, values, "idCliente=?", args);

    }

    public void delete(Cliente cliente){
        String[] args = {String.valueOf(cliente.getIdCliente())};

        getWritableDatabase().delete(TABELA_CLIENTES, "idCliente=?", args);
    }

    public List<Cliente> getListaClientesStatusFavorito(int status){

        Cursor cursor;

        List<Cliente> clientes = new ArrayList<Cliente>();

        if(status == 3){
            String[] args = new String[] {String.valueOf(1)};
            cursor = getWritableDatabase().query(TABELA_CLIENTES, COLUNAS_CLIENTES, FILTER_FAVORITO, args, null, null, "nome COLLATE NOCASE ASC");
        }
        else {
            String[] args = new String[] { String.valueOf(status)};
            cursor = getWritableDatabase().query(TABELA_CLIENTES, COLUNAS_CLIENTES, FILTER_STATUS, args, null, null, "nome COLLATE NOCASE ASC");
        }

        while(cursor.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setIdCliente(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setTelefone(cursor.getString(2));
            cliente.setCelular(cursor.getString(3));
            cliente.setEmail(cursor.getString(4));
            cliente.setEndereco(cursor.getString(5));
            cliente.setCidade(cursor.getString(6));
            cliente.setEstado(cursor.getString(7));
            cliente.setFoto(cursor.getString(8));
            cliente.setObservacao(cursor.getString(9));
            cliente.setStatus(cursor.getShort(10));
            cliente.setFavorito(cursor.getShort(11));

            clientes.add(cliente);
        }

        cursor.close();

        return clientes;
    }

    public Cliente getClienteId(int idCliente){
        Cliente cliente = new Cliente();

        Cursor cursor = null;

        String[] args = new String[] { String.valueOf(idCliente)};

        try {
            cursor = getReadableDatabase().query(TABELA_CLIENTES, COLUNAS_CLIENTES, FILTER_ID, args, null, null, null);

            while (cursor.moveToNext()){
                cliente.setIdCliente(cursor.getInt(cursor.getColumnIndex("idCliente")));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                cliente.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                cliente.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                cliente.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                cliente.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                cliente.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                cliente.setObservacao(cursor.getString(cursor.getColumnIndex("observacao")));
                cliente.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
                cliente.setFavorito(cursor.getInt(cursor.getColumnIndex("favorito")));



            }

            cursor = null;
        }catch (Exception e){

        };
        return cliente;

    }


}
