package ui;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.game.R;
import com.example.game.databinding.FragmentThirdBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FragmentThird extends Fragment {
    private FragmentThirdBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(inflater);
        getImages();
        takePictureFromGalleryOrAnyOtherFolder();
        return binding.getRoot();
    }

    private void takePictureFromGalleryOrAnyOtherFolder() {
        binding.imgNight.setOnClickListener(view -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            activityResultLauncher.launch(photoPickerIntent);
        });
    }

    public void getImages() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                try {
                    final Uri imageUri = result.getData().getData();
                    final InputStream imageStream = requireActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    binding.imgNight.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(requireActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        });
    }
}