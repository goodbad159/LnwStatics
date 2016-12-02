import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChart2DShow extends ApplicationFrame {
	private static double cs;
	private static double cls;
	private static double ncs;

	public PieChart2DShow(double cs, double cls, double ncs) {
		super("��ҿǧ��� 2D");
		this.cs = cs;
		this.cls = cls;
		this.ncs = ncs;
		setContentPane(createDemoPanel());
	}

	private static PieDataset createDataset() {
		final String comeStudents = "�ѡ���¹���������¹";
		final String comeLateStudents = "�ѡ���¹��������";
		final String notcomeStudents = "�ѡ���¹���Ҵ���¹";
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(notcomeStudents+" "+ncs+"%", new Double(ncs));
		dataset.setValue(comeLateStudents+" "+cls+"%", new Double(cls));
		dataset.setValue(comeStudents+" "+cs+"%", new Double(cs));

		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("����ૹ��ͧ�ѡ���¹㹡����������Ԩ����", dataset, true, true, false);
		return chart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

}
