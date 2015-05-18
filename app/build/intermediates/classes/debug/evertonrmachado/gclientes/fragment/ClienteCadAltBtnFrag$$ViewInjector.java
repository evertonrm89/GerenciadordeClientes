// Generated code from Butter Knife. Do not modify!
package evertonrmachado.gclientes.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ClienteCadAltBtnFrag$$ViewInjector<T extends evertonrmachado.gclientes.fragment.ClienteCadAltBtnFrag> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296357, "field 'btnCancelar'");
    target.btnCancelar = finder.castView(view, 2131296357, "field 'btnCancelar'");
    view = finder.findRequiredView(source, 2131296356, "field 'btnSalvar'");
    target.btnSalvar = finder.castView(view, 2131296356, "field 'btnSalvar'");
  }

  @Override public void reset(T target) {
    target.btnCancelar = null;
    target.btnSalvar = null;
  }
}
