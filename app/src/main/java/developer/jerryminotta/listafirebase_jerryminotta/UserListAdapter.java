package developer.jerryminotta.listafirebase_jerryminotta;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    ArrayList<Item> items;

    public UserListAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_renglon, null, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_titulo.setText(items.get(i).getNombre());
        viewHolder.tv_descripcion.setText(items.get(i).getDescripcion());
        viewHolder.cb_completado.setActivated(items.get(i).getCompletado());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_titulo, tv_descripcion;
        CheckBox cb_completado;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_descripcion= itemView.findViewById(R.id.tv_descripcion);
        tv_titulo = itemView.findViewById(R.id.tv_titulo);
        cb_completado = itemView.findViewById(R.id.cb_completado);

    }
}

}
