package ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.game.R;
import com.example.game.databinding.FragmentFirstBinding;

public class fragmentFirst extends Fragment {
    private FragmentFirstBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater);
        return binding.getRoot();
    }

}