package app.andanhm.foodworld.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.andanhm.foodworld.R;
import app.andanhm.foodworld.utils.KeyboardUtils;

/**
 *
 */
public class EmptyCartFragment extends Fragment {
    Activity mActivity;
    TextView textViewBrowserItem;
    private String TAG=getClass().getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeyboardUtils.hideKeyboard(mActivity);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.client_empty_cart_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewBrowserItem=(TextView)view.findViewById(R.id.textViewBrowserItem);
        textViewBrowserItem.setOnClickListener(cListener);
    }
    View.OnClickListener cListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textViewBrowserItem:
                    FragmentTransaction mFragmentTransaction=getFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.containerView, new FoodFragment(), TAG);
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                    break;
            }
        }
    };

}
