// Generated code from Butter Knife. Do not modify!
package evertonrmachado.gclientes.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ClienteExibirBtnFrag$$ViewInjector<T extends evertonrmachado.gclientes.fragment.ClienteExibirBtnFrag> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296363, "field 'btnLigar'");
    target.btnLigar = finder.castView(view, 2131296363, "field 'btnLigar'");
    view = finder.findRequiredView(source, 2131296364, "field 'btnEmail'");
    target.btnEmail = finder.castView(view, 2131296364, "field 'btnEmail'");
    view = finder.findRequiredView(source, 2131296365, "field 'btnMap'");
    target.btnMap = finder.castView(view, 2131296365, "field 'btnMap'");
    view = finder.findRequiredView(source, 2131296366, "field 'btnDeletar'");
    target.btnDeletar = finder.castView(view, 2131296366, "field 'btnDeletar'");
  }

  @Override public void reset(T target) {
    target.btnLigar = null;
    target.btnEmail = null;
    target.btnMap = null;
    target.btnDeletar = null;
  }
}
