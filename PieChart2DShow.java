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
		super("กราฟวงกลม 2D");
		this.cs = cs;
		this.cls = cls;
		this.ncs = ncs;
		setContentPane(createDemoPanel());
	}

	private static PieDataset createDataset() {
		final String comeStudents = "นักเรียนที่เข้าเรียน";
		final String comeLateStudents = "นักเรียนที่มาสาย";
		final String notcomeStudents = "นักเรียนที่ขาดเรียน";
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(notcomeStudents+" "+ncs+"%", new Double(ncs));
		dataset.setValue(comeLateStudents+" "+cls+"%", new Double(cls));
		dataset.setValue(comeStudents+" "+cs+"%", new Double(cs));

		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("เปอร์เซนต์ของนักเรียนในการเข้าร่วมกิจกรรม", dataset, true, true, false);
		return chart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

}
