package com.judacribz.amazonbooks.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.judacribz.amazonbooks.R;
import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;
import com.judacribz.amazonbooks.view.activities.DetailActivity;
import com.judacribz.amazonbooks.view.activities.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AmazonBooksAdapter extends RecyclerView.Adapter<AmazonBooksAdapter.AmazonBookHolder> {

    private List<Book> amazonBooks;

    public AmazonBooksAdapter(List<Book> amazonBooks) {
        this.amazonBooks = amazonBooks;
    }

    @NonNull
    @Override
    public AmazonBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AmazonBookHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_book,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull AmazonBookHolder holder, int position) {
        holder.bindViews(amazonBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return amazonBooks != null ? amazonBooks.size() : 0;
    }

    public void setBooks(List<Book> books) {
        this.amazonBooks = books;
        notifyDataSetChanged();
    }

    class AmazonBookHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivBookThumbnail)
        ImageView ivBookThumbnail;
        @BindView(R.id.tvBookTitle)
        TextView tvBookTitle;
        @BindView(R.id.tvBookAuthor)
        TextView tvBookAuthor;

        Context context;

        AmazonBookHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

            itemView.setOnClickListener(
                    v -> context.startActivity(new Intent(context, DetailActivity.class))
            );
        }

        void bindViews(Book book) {
            Picasso
                    .get()
                    .load(book.getImageURL())
                    .into(ivBookThumbnail);
            tvBookTitle.setText(book.getTitle());
            final String bookAuthor = book.getAuthor();
            if (bookAuthor != null) {
                tvBookAuthor.setText(String.format(
                        Locale.US,
                        context.getString(R.string.str_author),
                        bookAuthor
                ));
            }
        }
    }

}
