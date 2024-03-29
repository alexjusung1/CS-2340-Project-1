package com.example.cs2340project1.ui.classes;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cs2340project1.R;

public class RecyclerClassTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ClassesAdapter adapter;

    public RecyclerClassTouchHelper(ClassesAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Class");
            builder.setMessage("Are you sure you want to delete this Class?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.deleteItem(position);
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            adapter.editItem(position);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView RV, @NonNull RecyclerView.ViewHolder VH, float dx, float dy, int state, boolean isactive) {
        super.onChildDraw(canvas, RV, VH, dx, dy, state, isactive);

        Drawable icon;
        ColorDrawable background;

        View itemView = VH.itemView;
        int offset = 20;

        if (dx > 0) {
            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_edit);
            background = new ColorDrawable(ContextCompat.getColor(adapter.getContext(), R.color.darkGreenHolo));
        } else {
            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_delete);
            background = new ColorDrawable(Color.RED);
        }

        assert icon != null;
        int iconMargin = (itemView.getHeight()-icon.getIntrinsicHeight())/2;
        int iconTop = itemView.getTop()+(itemView.getHeight()-icon.getIntrinsicHeight())/2;
        int iconBottom = iconTop+icon.getIntrinsicHeight();

        if (dx>0) {
            int iconLeft = itemView.getLeft()+iconMargin;
            int iconRight = itemView.getLeft()+iconMargin+icon.getIntrinsicWidth();
            icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft()+((int)dx)+offset,itemView.getBottom());
        } else if (dx<0) {
            int iconLeft = itemView.getRight()-iconMargin-icon.getIntrinsicWidth();
            int iconRight = itemView.getRight()-iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight()+((int)dx)-offset,itemView.getTop(),itemView.getRight(),itemView.getBottom());
        } else {
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(canvas);
        icon.draw(canvas);
    }
}
