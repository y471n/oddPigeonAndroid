package com.example.mac.oddpigeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TutorialFragment extends Fragment {

    private ViewGroup mContainerView;
    private Button mSignupButton;

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;


    public TutorialFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static TutorialFragment create(int pageNumber) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView;
        switch (mPageNumber){
            case 0:
                rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial, container, false);
                break;
            case 1:
                rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial2, container, false);
                break;
            case 2:
                rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial3, container, false);
                setSeenTutorialTrue();
                startFbActivity(rootView);
                break;
            default:
                rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial, container, false);
                break;
        }

        return rootView;
    }

    private void setSeenTutorialTrue() {
        SharedPreferences settings = this.getActivity().getSharedPreferences(SplashActivity.PREFS_NAME, 0);
        settings.edit().putBoolean("seenTutorial", true).apply();
    }

    private void startFbActivity(View rootView) {
        mSignupButton = (Button) rootView.findViewById(R.id.signup_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
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
