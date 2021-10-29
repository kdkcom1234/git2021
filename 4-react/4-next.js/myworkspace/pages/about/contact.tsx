import Layout from "../../components/layout";
import Sidebar from "../../components/about/sidebar";
export default function Contact() {
  return (
    <Layout>
      <article className="d-flex">
        <Sidebar />
        <section>
          <p>Contact Page</p>
        </section>
      </article>
    </Layout>
  );
}
