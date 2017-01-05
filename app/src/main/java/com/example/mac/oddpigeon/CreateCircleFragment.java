package com.example.mac.oddpigeon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Activities that contain this fragment must implement the
 * {@link CreateCircleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateCircleFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CreateCircleFragment extends Fragment {

    private Button createOpenCircleButton;
    private Button createClosedCircleButton;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static CreateCircleFragment newInstance(String param1, String param2) {
        CreateCircleFragment fragment = new CreateCircleFragment();
        return fragment;
    }
    public CreateCircleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View createCircleView;
        createCircleView = inflater.inflate(R.layout.fragment_create_circles, container, false);

        createOpenCircleButton = (Button) createCircleView.findViewById(R.id.create_open_group_button);
        createClosedCircleButton = (Button) createCircleView.findViewById(R.id.create_closed_group_button);
        createOpenCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateCircleActivity.class);
                intent.putExtra(CreateCircleActivity.CIRCLE_TYPE, "open");
                startActivity(intent);
            }
        });
        createClosedCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateCircleActivity.class);
                intent.putExtra(CreateCircleActivity.CIRCLE_TYPE, "closed");
                startActivity(intent);
            }
        });

        return createCircleView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
